(ns {{name}}.core
    (:gen-class))

(defn parse-input
  [input])

(defn read-input
  []
  (parse-input (slurp "resources/input")))

(defn -main
  [& args]
  (println "Hello, World!"))
