(ns day17.simulation
  (:require [clojure.core.match :refer [match]]
            [clojure.math.combinatorics :as combo]
            [clojure.set :as set]))

(defn count-active
  [cubes]
  (->> cubes
       (filter true?)
       count))

(defn run-cycle-cube
  [cube neighbors]
  (match [cube (count-active neighbors)]
    [true (:or 2 3)] true
    [false 3] true
    :else false))

(defn get-cube
  [space {:keys [x y z w]}]
  (get-in space [w z y x] false))

(defn get-neighbor-locs
  [loc]
  (let [{:keys [x y z w]} loc]
    (->>
     (combo/cartesian-product [(- x 1) x (+ x 1)]
                              [(- y 1) y (+ y 1)]
                              [(- z 1) z (+ z 1)]
                              [(- w 1) w (+ w 1)])
     (map (fn [[x y z w]] {:x x, :y y, :z z, :w w}))
     (filter #(not= % loc))
     set)))

(defn get-all-locs
  [four-space]
  (let [ws (set (keys four-space))
        zs (set (mapcat (fn [[_ space]] (keys space)) four-space))
        ys (set (mapcat (fn [[_ space]]
                          (mapcat (fn [[_ plane]] (keys plane)) space))
                        four-space))
        xs (set (mapcat (fn [[_ space]]
                          (mapcat (fn [[_ plane]]
                                    (mapcat (fn [[_ line]] (keys line))
                                            plane))
                                  space))
                        four-space))]
    (->>
     (combo/cartesian-product xs ys zs ws)
     (map (fn [[x y z w]] {:x x, :y y, :z z, :w w}))
     set)))

(defn get-active-locs
  [space]
  (set (filter #(get-cube space %) (get-all-locs space))))

(defn find-cubes-to-cycle
  [space]
  (let [active-locs (get-active-locs space)]
    (apply set/union
           (set active-locs)
           (map get-neighbor-locs active-locs))))

(defn assoc-cube
  [four-space {:keys [x y z w]} cube]
  (let [space (get four-space w (sorted-map))
        plane (get space z (sorted-map))
        line (get plane y (sorted-map))]
    (assoc four-space w
           (assoc space z
                  (assoc plane y
                         (assoc line x cube))))))

(defn run-cycle
  [space]
  (reduce (fn [new-space loc]
            (let [cube (get-cube space loc)
                  neighbors (map #(get-cube space %) (get-neighbor-locs loc))]
              (assoc-cube new-space
                          loc
                          (run-cycle-cube cube neighbors))))
          (sorted-map)
          (find-cubes-to-cycle space)))
