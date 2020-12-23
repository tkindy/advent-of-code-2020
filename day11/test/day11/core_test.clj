(ns day11.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [day11.core :as c]))

(defn read-example
  [name]
  (str/trim (slurp (str "resources/" name))))

(def example-string (read-example "example"))
(def example (c/parse-input example-string))

(deftest seat-map->string
  (is (= (c/seat-map->string example)
         example-string)))

(deftest simulate-one-spot
  (is (= (c/simulate-one-spot {:x 5 :y 1}
                              (c/parse-input (read-example "example-simulated1")))
         'empty)))

(deftest get-neighbors
  (is (= (c/get-neighbors {:x 8 :y 4} (c/parse-input (read-example "example-simulated2")))
         ['floor 'empty    'occupied
          'floor           'empty
          'floor 'occupied 'occupied])
      (= (c/get-neighbors {:x 0 :y 0} (c/parse-input (read-example "example-simulated2")))
         ['floor 'occupied 'empty])))

(deftest get-seat
  (is (= (c/get-seat {:x 0 :y 0} example) 'empty))
  (is (= (c/get-seat {:x 3 :y 2} example) 'floor))
  (is (= (c/get-seat {:x 6 :y 5} (c/parse-input (read-example "example-simulated2"))) 'occupied)))

(deftest get-neighbor-locs
  (is (= (c/get-neighbor-locs {:x 4 :y 8} 12 15)
         [{:x 3 :y 7} {:x 4 :y 7} {:x 5 :y 7}
          {:x 3 :y 8}             {:x 5 :y 8}
          {:x 3 :y 9} {:x 4 :y 9} {:x 5 :y 9}])))

(deftest simulate-one
  (is (= (c/seat-map->string (c/simulate-one example))
         (read-example "example-simulated1")))
  (is (= (c/seat-map->string (c/simulate-one (c/simulate-one example)))
         (read-example "example-simulated2"))))
