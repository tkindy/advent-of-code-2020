(ns day17.printing-test
  (:require [clojure.test :refer :all]
            [day17.common-test :refer [example-state example-parsed example-cycled]]
            [day17.printing :refer [space->string]]))

(deftest test-space->string
  (is (= (space->string example-parsed)
         (str "z=0\n"
              example-state)))
  (is (= (space->string example-cycled)
         (str "z=-1\n"
              "#..\n"
              "..#\n"
              ".#.\n"
              "\n"
              "z=0\n"
              "#.#\n"
              ".##\n"
              ".#.\n"
              "\n"
              "z=1\n"
              "#..\n"
              "..#\n"
              ".#."))))
