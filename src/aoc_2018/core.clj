(ns aoc-2018.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn read-input [file]
  (slurp (io/resource file)))

(defn read-list-of-numbers [s]
  (->> (str/split s #"\n")
       (map read-string)))

(defn frequency-calibrator [input]
  (->> (read-list-of-numbers input)
       (reduce +)
       (str)))

(defn find-frequency-calibrator-duplicate [input]
  (->> (read-list-of-numbers input)
       (cycle)
       (reduce (fn [{:keys [seen freq]} num]
                 (let [new-freq (+ freq num)]
                   (if (contains? seen new-freq)
                     (reduced new-freq)
                     {:seen (conj seen new-freq) :freq new-freq})))
               {:seen #{0} :freq 0})
       (str)))

(defn -main
  [& args]
  (println "Final frequency:" (frequency-calibrator (read-input "input-1.txt")))
  (println "First frequency duplicate:" (find-frequency-calibrator-duplicate (read-input "input-1.txt"))))
