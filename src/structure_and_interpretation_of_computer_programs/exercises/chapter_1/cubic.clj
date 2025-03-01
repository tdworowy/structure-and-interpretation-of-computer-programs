(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.cubic
  (:require [structure-and-interpretation-of-computer-programs.abstraction-with-procedures.fixed-point :refer [newton-method]]))

(load-file "../math/fixed_point.clj")

(defn cubic [a b c]
  (fn [x] (+ (* x x x) (* a x x) (* b x) c)))

(println (newton-method (cubic 1 1 1) 1))