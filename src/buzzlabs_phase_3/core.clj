(ns buzzlabs-phase-3.core
  (:require [datomic.client.api :as d])
  (:gen-class))

(def client (d/client {:server-type :datomic-local
                       :system "datomic-samples"}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (d/list-databases client {}))
  (println "Hello, World!"))
