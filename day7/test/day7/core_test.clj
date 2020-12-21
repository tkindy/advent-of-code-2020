(ns day7.core-test
  (:require [clojure.test :refer :all]
            [day7.core :refer [parse-input parse-rule]]))

(def example-rules
  "light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.")
(def example-parsed
  [{:color "light red" :contents [{:color "bright white" :count 1}
                                  {:color "muted yellow" :count 2}]}
   {:color "dark orange" :contents [{:color "bright white" :count 3}
                                    {:color "muted yellow" :count 4}]}
   {:color "bright white" :contents [{:color "shiny gold" :count 1}]}
   {:color "muted yellow" :contents [{:color "shiny gold" :count 2}
                                     {:color "faded blue" :count 9}]}
   {:color "shiny gold" :contents [{:color "dark olive" :count 1}
                                   {:color "vibrant plum" :count 2}]}
   {:color "dark olive" :contents [{:color "faded blue" :count 3}
                                   {:color "dotted black" :count 4}]}
   {:color "vibrant plum" :contents [{:color "faded blue" :count 5}
                                     {:color "dotted black" :count 6}]}
   {:color "faded blue" :contents []}
   {:color "dotted black" :contents []}])

(deftest test-parse-rule
  (is (= (parse-rule "light red bags contain 1 bright white bag, 2 muted yellow bags.")
         {:color "light red" :contents [{:color "bright white" :count 1}
                                        {:color "muted yellow" :count 2}]})))

(deftest test-parse-input
  (is (= (parse-input example-rules) example-parsed)))
