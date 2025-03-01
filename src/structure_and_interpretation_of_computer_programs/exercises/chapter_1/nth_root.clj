(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.nth_root
  (:require [structure-and-interpretation-of-computer-programs.abstraction-with-procedures.fixed-point :refer [average-damp fixed-point]]))

(defn compose [f g]
  (fn [x] (f (g x))))

(defn repeated-iterative [f n]
  (loop [current-f f
         i 1]
    (if (= i n)
      current-f
      (recur (compose f current-f) (inc i)))))

(defn log2 [n]
  (/ (Math/log n) (Math/log 2)))

(defn nth-root [x n]
  (let [num-iterations (Math/floor (log2 n))
        damped-fn (repeated-iterative (average-damp (fn [y] (/ x (Math/pow y (- n 1)))))
                                      num-iterations)]
    (fixed-point damped-fn 1.0)))

(println (nth-root  81 4))