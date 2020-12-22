(ns day7.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def rule-pattern #"([a-z ]+) bags contain (.*)\.")
(def content-pattern #"(\d+) ([a-z ]+) bags?")

(defn parse-content
  [content]
  (let [[_ count color] (re-matches content-pattern content)]
    {:color color :count (Integer/parseInt count)}))

(defn parse-contents
  [contents]
  (if (= contents "no other bags")
    []
    (let [splits (str/split contents #", ")]
      (map parse-content splits))))

(defn parse-rule
  [line]
  (let [[_ color contents] (re-matches rule-pattern line)
        contents (parse-contents contents)]
    {:color color :contents contents}))

(defn parse-input
  [input]
  (map parse-rule (str/split-lines input)))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn build-container-pairs
  [rule]
  (map (fn [content] [(:color rule) (:color content)])
       (:contents rule)))

(defn build-container-index
  [rules]
  (->> rules
       (mapcat build-container-pairs)
       (reduce (fn [acc [color content]]
                 (update acc content
                         #(if (nil? %)
                            #{color}
                            (conj % color))))
               {})))

(defn expand-contents
  [contents]
  (mapcat (fn [{:keys [color count]}]
            (->> (range count)
                 (map (fn [_] color))))
          contents))

(defn build-contents-index
  [rules]
  (reduce (fn [acc {:keys [color contents]}]
            (assoc acc color (expand-contents contents)))
          {}
          rules))

(defn traverse-bags
  [accumulate init-acc color index]
  (loop [acc init-acc, frontier [color]]
    (if (empty? frontier)
      acc
      (let [color (first frontier)]
        (recur (accumulate acc color)
               (apply conj (rest frontier) (get index color [])))))))

(defn find-containers
  [color rules]
  (let [found (traverse-bags conj #{} color
                             (build-container-index rules))]
    (disj found color)))

(defn count-bag-inner-bags
  [color rules]
  (let [rule (first (filter #(= (:color %) color) rules))]
    (->> (:contents rule)
         (map :count)
         (reduce + 0))))

(defn count-inner-bags
  [color rules]
  (traverse-bags (fn [acc _] (+ acc 1))
                 -1 color
                 (build-contents-index rules)))

(defn -main
  [& args]
  (let [rules (read-input)]
    (println "Part 1:" (count (find-containers "shiny gold" rules)))
    (println "Part 2:" (count-inner-bags "shiny gold" rules))))
