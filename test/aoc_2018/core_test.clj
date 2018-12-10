(ns aoc-2018.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc-2018.core :refer :all]))

(defn lines [& strings] (str/join "\n" strings))

(deftest frequency-calibrator-test
  (testing
    (let [data (lines "+1" "-2" "+3" "+1")]
      (is (= "3" (frequency-calibrator data))))))

(deftest find-frequency-calibrator-duplicate-test
  (testing
      (doseq [{:keys [input expected]} [{:input (lines "+1" "-2" "+3" "+1") :expected "2"}
                                        {:input (lines "+1" "-1") :expected "0"}
                                        {:input (lines "+3" "+3" "+4" "-2" "-4") :expected "10"}
                                        {:input (lines "-6" "+3" "+8" "+5" "-6") :expected "5"}
                                        {:input (lines "+7" "+7" "-2" "-7" "-4") :expected "14"}]]
        (is (= expected (find-frequency-calibrator-duplicate input))))))

(deftest box-id-checksum-test
  (testing
      (is (= "12" (box-hash-sum (lines "abcdef"
                                     "bababc"
                                     "abbcde"
                                     "abcccd"
                                     "aabcdd"
                                     "abcdee"
                                     "ababab"))))))
(deftest box-id-checksum-test
  (testing
      (is (= "fgij" (common-letters-for-ids-differing-one-character (lines "abcde"
                                                                           "fghij"
                                                                           "axcye"
                                                                           "fguij"))))))
