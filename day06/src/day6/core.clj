(ns day06.core
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

(defn all-yes
  [group]
  (apply set/intersection group))

(defn sum
  [l]
  (reduce + 0 l))

(defn -main
  [& args]
  (let [groups (read-input)]
    (println "Part 1:" (->> groups
                            (map (comp count any-yes))
                            sum))
    (println "Part 2:" (->> groups
                            (map (comp count all-yes))
                            sum))))
