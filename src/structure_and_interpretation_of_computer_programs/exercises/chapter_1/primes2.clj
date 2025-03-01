(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.primes2
  (:require [structure-and-interpretation-of-computer-programs.abstraction-with-procedures.prime-fermat-test :refer [fast-prime?]]))

(defn report-prime [elapsed-time]
  (print "***")
  (print elapsed-time))

(defn start-prime-test [n start-time]
  (when (fast-prime? n 20)
    (report-prime (/ (- (System/nanoTime) start-time) 1e6))))

(defn time-prime-test [n]
  (println)
  (print n)
  (start-prime-test n (System/nanoTime)))

(defn search-for-prime [start, end]
  (when (< start end)
    (time-prime-test start)
    (search-for-prime (+ start 1) end)))

(search-for-prime 2000, 3000)
(search-for-prime 1000, 1100)
(search-for-prime 10000, 10100)
(search-for-prime 1000000, 1000100)
(search-for-prime 10000000, 10000100)
(search-for-prime 100000000, 100000100)
