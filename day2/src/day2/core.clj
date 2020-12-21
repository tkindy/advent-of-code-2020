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

(defn valid?
  [pass]
  (let [count (count-occurrences (:password pass) (:letter pass))]
    (<= (:min-count pass)
        count
        (:max-count pass))))

(defn count-valid
  [ps]
  (->> ps
       (filter valid?)
       count))

(defn -main
  [& args]
  (let [ps (read-input)]
    (println (count-valid ps))))
