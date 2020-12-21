(ns day3.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.core.match :refer [match]]))

(defn parse-line
  [line]
  (map (fn [c]
         (match c
           \. 'open
           \# 'tree))
       line))

(defn read-input
  []
  (with-open [rdr (io/reader "resources/input")]
    (->> rdr
         line-seq
         (reduce conj [])
         (map parse-line))))

(defn -main
  [& args]
  (println "Hello, World!"))
