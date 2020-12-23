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
  (is (= (c/get-neighbors {:x 7 :y 5} (c/parse-input (read-example "example-simulated2")))
         ['empty 'empty    'empty
          'empty           'empty
          'empty 'empty 'occupied])
      (= (c/get-neighbors {:x 0 :y 0} (c/parse-input (read-example "example-simulated2")))
         ['empty 'occupied 'empty])))

(deftest get-seat
  (is (= (c/get-seat {:x 0 :y 0} example) 'empty))
  (is (= (c/get-seat {:x 3 :y 2} example) 'floor))
  (is (= (c/get-seat {:x 0 :y 1} (c/parse-input (read-example "example-simulated2"))) 'occupied)))

(deftest simulate-one
  (is (= (c/seat-map->string (c/simulate-one example))
         (read-example "example-simulated1")))
  (is (= (c/seat-map->string (c/simulate-one (c/simulate-one example)))
         (read-example "example-simulated2"))))
