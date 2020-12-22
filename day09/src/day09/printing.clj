(ns day09.printing
  (:require [clojure.string :as str]
            [day09.simulation :refer [get-active-locs get-cube]]))

(defn cube->string
  [cube]
  (if cube "#" "."))

(defn line->string
  [line]
  (let [cubes (map (fn [[_ cube]] (cube->string cube))
                   line)]
    (str/join cubes)))

(defn plane->string
  [plane]
  (let [lines (map (fn [[_ line]] (line->string line))
                   plane)]
    (str/join "\n" lines)))

(defn build-range
  [nums]
  (range (apply min nums)
         (+ (apply max nums) 1)))

(defn draw-line
  [space z y xs]
  (let [cubes  (->> xs
                    (map #(get-cube space {:x % :y y :z z}))
                    (map cube->string))]
    (str/join cubes)))

(defn draw-plane
  [space z ys xs]
  (let [lines (map #(draw-line space z % xs) ys)]
    (str/join "\n" lines)))

(defn space->string
  [space]
  (let [active-locs (get-active-locs space)
        xs (build-range (map :x active-locs))
        ys (build-range (map :y active-locs))
        zs (build-range (map :z active-locs))
        planes (map #(str "z=" % "\n"
                          (draw-plane space % ys xs)) zs)]
    (str/join "\n\n" planes)))
