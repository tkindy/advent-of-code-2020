(ns day2.core
  (:gen-class))

(defn parse-line
  [line]
  (let [[_ min-count max-count letter password] (re-find #"^(\d+)-(\d+) (\w): (\w+)$" line)]
    {:min-count (Integer/parseInt min-count)
     :max-count (Integer/parseInt max-count)
     :letter (.charAt letter 0) :password password}))

(defn read-input
  []
  (with-open [rdr (clojure.java.io/reader "resources/input")]
    (map parse-line rdr)))

(defn count-occurrences
  [s c]
  (->> s
       (filter (fn [l] (= l c)))
       count))

(defn -main
  [& args]
  (println "Hello, World!"))
