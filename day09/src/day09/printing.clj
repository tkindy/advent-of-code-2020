(ns day09.printing
  (:require [clojure.string :as str]))

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

(defn space->string
  [space]
  (let [planes (map (fn [[z plane]]
                      (str "z=" z "\n"
                           (plane->string plane)))
                    space)]
    (str/join "\n\n" planes)))
