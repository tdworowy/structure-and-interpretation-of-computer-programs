(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.cont-frac
  (:require [clojure.math :as math]))

(defn cont-frac-recur [n d k]
  (letfn [(recur-fn [i]
            (if (= k i) (/ (n i) (d i))
                (/ (n i) (+ (d i) (recur-fn (+ 1 i))))))]
    (recur-fn 1)))

(defn cont-frac-iter [n d k]
  (letfn [(iter-fn [i result]
            (if (= 0 i) result
                (iter-fn (- i 1) (/ (n i) (+ result (d i))))))]
    (iter-fn (- k 1) (/ (n k) (d k)))))

(println (cont-frac-recur (fn [i] 1.0)
                          (fn [i] 1.0)
                          100))

(println (cont-frac-iter (fn [i] 1.0)
                         (fn [i] 1.0)
                         100))

(defn d-euler [i]
  (if (= (mod i 3) 2)
    (* 2 (/ (+ i 1) 3))
    1))

(println (cont-frac-recur (fn [i] 1.0)
                          d-euler
                          100))

(println (cont-frac-iter (fn [i] 1.0)
                         d-euler
                         100))

(defn tan-cf [x k]
  (cont-frac-iter
   (fn [i] (if (= i 1) x (* x x -1)))
   (fn [i] (- (* 2.0 i) 1))
   k))

(println (tan-cf 1 10))
(println (math/tan 1))