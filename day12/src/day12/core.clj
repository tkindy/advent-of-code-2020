(ns day12.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]
            [clojure.string :as str]
            [clojure.math.numeric-tower :refer [abs]]))

(defn parse-instruction
  [instruction]
  (let [[_ action value] (re-matches #"(N|S|E|W|L|R|F)(\d+)" instruction)]
    {:action action :value (Integer/parseInt value)}))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map parse-instruction)
       (reduce conj [])))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn loc+
  [{:keys [x y]} dx dy]
  {:x (+ x dx) :y (+ y dy)})

(def lefts
  {"E" "N", "N" "W", "W" "S", "S" "E"})
(def rights
  {"E" "S", "S" "W", "W" "N", "N" "E"})

(defn rotate
  [dir towards degrees]
  (let [num-turns (/ degrees 3)
        turn-map (match towards
                   "R" rights
                   "L" lefts)]
    (reduce turn-map dir (range num-turns))))

(defn navigate-one
  [ship {:keys [action value]}]
  (let [{:keys [loc dir]} ship]
    (match action
      "N" (assoc ship :loc (loc+ loc 0 (- 0 value)))
      "S" (assoc ship :loc (loc+ loc 0 value))
      "E" (assoc ship :loc (loc+ loc value 0))
      "W" (assoc ship :loc (loc+ loc (- 0 value) 0))
      (:or "L" "R") (assoc ship :dir (rotate dir action value))
      "F" (navigate-one ship {:action dir :value value}))))

(defn navigate
  [instructions]
  (reduce navigate-one
          {:loc {:x 0 :y 0}, :dir "E"}
          instructions))

(defn distance
  [ship]
  (let [{:keys [loc]} ship
        {:keys [x y]} loc]
    (+ (abs x) (abs y))))

(defn -main
  [& args]
  (let [instructions (read-input)]
    (println "Part 1:" (distance (navigate instructions)))))
