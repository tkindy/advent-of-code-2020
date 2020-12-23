(ns day11.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]
            [clojure.math.combinatorics :as combo]))

(defn parse-seat
  [seat]
  (match seat
    \. 'floor
    \L 'empty
    \# 'occupied))

(defn parse-row
  [row]
  (->> row
       (map-indexed (fn [index seat] [index (parse-seat seat)]))
       (apply conj {})))

(defn parse-input
  [input]
  (->> input
       str/split-lines
       (map-indexed (fn [index row] [index (parse-row row)]))
       (apply conj {})))

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn get-seat
  [{:keys [x y]} seat-map]
  (get-in seat-map [y x]))

(defn filter-to-range
  [nums max]
  (filter #(<= 0 % (- max 1)) nums))

(defn loc+
  [{x1 :x y1 :y} {x2 :x y2 :y}]
  {:x (+ x1 x2) :y (+ y1 y2)})

(def vectors
  (->> (combo/cartesian-product [-1 0 1] [-1 0 1])
       (map (fn [[y x]] {:x x :y y}))
       (filter #(not= % {:x 0 :y 0}))))

(defn get-possible-neighbor-locs
  [loc vector width height]
  (loop [cur loc, acc []]
    (let [next (loc+ cur vector)]
      (if (and (<= 0 (:x next) (dec width))
               (<= 0 (:y next) (dec height)))
        (recur next (conj acc next))
        acc))))

(defn get-neighbors
  [loc seat-map]
  (let [width (count (get seat-map 0))
        height (count seat-map)]
    (map (fn [vector]
           (let [locs (get-possible-neighbor-locs loc vector width height)]
             (->> locs
                  (map #(get-seat % seat-map))
                  (some #{'empty 'occupied}))))
         vectors)))

(defn count-occupied
  [seats]
  (->> seats
       (filter #(= % 'occupied))
       count))

(defn simulate-one-spot
  [loc seat-map]
  (let [seat (get-seat loc seat-map)
        neighbors (get-neighbors loc seat-map)]
    (match [seat (count-occupied neighbors)]
      ['empty 0] 'occupied
      ['occupied (true :<< #(>= % 5))] 'empty
      :else seat)))

(defn get-all-spots
  [seat-map]
  (->> (combo/cartesian-product (range (count (get seat-map 0)))
                                (range (count seat-map)))
       (map (fn [[x y]] {:x x :y y}))))

(defn simulate-one
  [seat-map]
  (reduce (fn [new-map loc]
            (let [{:keys [x y]} loc]
              (assoc-in new-map [y x] (simulate-one-spot loc seat-map))))
          {}
          (get-all-spots seat-map)))

(defn simulate
  [seat-map]
  (loop [cur-map seat-map, last-map nil]
    (if (= cur-map last-map)
      cur-map
      (recur (simulate-one cur-map) cur-map))))

(defn count-occupied-map
  [seat-map]
  (->> seat-map
       vals
       (map #(count-occupied (vals %)))
       (apply +)))

(defn sort-map
  [m]
  (apply sorted-map (flatten (seq m))))

(defn spot->string
  [spot]
  (match spot
    'floor "."
    'empty "L"
    'occupied "#"))

(defn row->string
  [row]
  (str/join (map spot->string (vals (sort-map row)))))

(defn seat-map->string
  [seat-map]
  (str/join "\n"
            (map row->string (vals (sort-map seat-map)))))

(defn -main
  [& args]
  (let [seat-map (read-input)]
    (println "Part 2:" (count-occupied-map (simulate seat-map)))))
