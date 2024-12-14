(defn _map [proc items]
  (if (empty? items) nil
      (cons (proc (first items)) (_map proc (rest items)))))

(println (_map inc [2 3 4 5 6]))
(println (_map (fn [x] (* 2 x)) [2 3 4 5 6]))