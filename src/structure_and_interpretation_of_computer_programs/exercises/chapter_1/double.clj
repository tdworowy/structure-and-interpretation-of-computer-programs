(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.double)

(defn double-fn [f]
  (fn [x]
    (f (f x))))

(println ((double-fn inc) 5))
(println (((double-fn double-fn) inc) 5))
(println ((((double-fn double-fn) double-fn) inc) 5))
(println (((double-fn (double-fn double-fn)) inc) 5))