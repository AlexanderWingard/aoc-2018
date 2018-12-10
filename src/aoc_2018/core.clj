(ns aoc-2018.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.data :as data])
  (:gen-class))

(defn read-input [file]
  (slurp (io/resource file)))

(defn read-list-of-numbers [s]
  (->> (str/split s #"\n")
       (map read-string)))

(defn read-list-of-strings [s]
  (str/split s #"\n"))

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

(defn count-number-of [x coll]
  (count (filter #(some #{x} %) coll)))

(defn box-hash-sum [input]
  (->> (read-list-of-strings input)
       (map #(vals (frequencies %)))
       ((fn [freqs] (* (count-number-of 2 freqs)
                       (count-number-of 3 freqs))))
       (str)))

(defn common-letters-for-ids-differing-one-character [input]
  (->> (read-list-of-strings input)
       (map vec)
       ((fn [char-vecs]
          (mapcat (fn [char-vec1]
                    (mapcat (fn [char-vec2]
                              (let [[_ _ common] (data/diff char-vec2 char-vec1)]
                                (if (and (= (count common) (count char-vec1))
                                         (= 1 (count (filter nil? common))))
                                  [(apply str common)])))
                            char-vecs))
                  char-vecs)))
       (into #{})
       (str/join "\n")))

(defn -main
  [& args]
  (println "Final frequency:" (time (frequency-calibrator (read-input "input-1.txt"))))
  (println "First frequency duplicate:" (time(find-frequency-calibrator-duplicate (read-input "input-1.txt"))))
  (println "Checksum for all boxes:" (time (box-hash-sum (read-input "input-2.txt"))))
  (println "Common letters for boxes that only differs in one position:" (time (common-letters-for-ids-differing-one-character (read-input "input-2.txt")))))
