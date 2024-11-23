(defn double-fn [f]
  (fn [x]
    (f (f x))))

(println ((double-fn inc) 5))
(println (((double-fn double-fn) inc) 5))
(println ((((double-fn double-fn) double-fn) inc) 5))
(println (((double-fn (double-fn double-fn)) inc) 5))