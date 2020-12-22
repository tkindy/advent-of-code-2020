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

(defn find-containers
  [color rules]
  (let [index (build-container-index rules)
        found (loop [found #{}, frontier [color]]
                (if (empty? frontier)
                  found
                  (let [color (first frontier)]
                    (if (contains? found color)
                      (recur found (rest frontier))
                      (recur (conj found color)
                             (apply conj (rest frontier) (get index color [])))))))]
    (disj found color)))

(defn -main
  [& args]
  (println "Hello, World!"))
