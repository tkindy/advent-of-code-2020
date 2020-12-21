(ns day6.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-person
  [person]
  (set person))

(defn parse-group
  [group]
  (map parse-person (str/split group #"\n")))

(defn parse-input
  [input]
  (map parse-group (str/split input #"\n\n")))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn any-yes
  [group]
  (apply set/union group))

(defn -main
  [& args]
  (let [groups (read-input)
        yes-counts (map (comp count any-yes) groups)]
    (println "Part 1:" (reduce + 0 yes-counts))))
