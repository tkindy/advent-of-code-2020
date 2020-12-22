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

(defn traverse-containers
  [color rules color->acc]
  (let [index (build-container-index rules)
        acc (loop [acc [], frontier [color]]
              (if (empty? frontier)
                acc
                (let [color (first frontier)]
                  (recur (conj acc (color->acc color))
                         (apply conj (rest frontier) (get index color []))))))]
    acc))

(defn find-containers
  [color rules]
  (let [found (traverse-containers color rules
                                   (fn [color] color))]
    (disj (set found) color)))

(defn -main
  [& args]
  (let [rules (read-input)]
    (println "Part 1:" (count (find-containers "shiny gold" rules)))))
