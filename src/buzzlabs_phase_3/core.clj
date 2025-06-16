(ns buzzlabs-phase-3.core
  (:require [datomic.client.api :as d])
  (:gen-class))

(def client (d/client {:server-type :datomic-local
                       :system "datomic-samples"}))

(def db-name "counter-db")

(def conn (d/connect client {:db-name db-name}))

(def counter-schema
  [{:db/ident       :counter/id
    :db/valueType   :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "identifier for the value"}
   {:db/ident       :counter/value
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc "counter value"}])

(d/transact conn {:tx-data counter-schema})

(defn initialize-counter
  "initializes the counter if it does not exist"
  [conn]
  (let [db (d/db conn)
        has-counter (d/q '[:find ?value  :where [?c :counter/value ?value]] db)]
    (println "Checking for existing counter...")
    (when-not has-counter
      (println "Counter not found, initializing to 0")
      (d/transact conn {:tx-data [{:counter/id :counter
                                   :counter/value 0}]}))))


(defn -main
  "I don't do a whole lot ... yet."
  []
  (initialize-counter conn))


