(ns leiningen.new.aoc-clj
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             sanitize-ns multi-segment]]
            [leiningen.core.main :as main]))

(def render (renderer "aoc-clj"))

(defn aoc-clj
  "FIXME: write documentation"
  [name]
  (let [main-ns (multi-segment (sanitize-ns name))
        data {:name name
              :sanitized (name-to-path main-ns)}]
    (main/info "Generating fresh 'lein new' aoc-clj project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{sanitized}}.clj" (render "core.clj" data)]
             ["test/{{sanitized}}_test.clj" (render "test.clj" data)]
             "resources")))
