(defn unique-triples [n]
  (mapcat
   (fn [i]
     (mapcat
      (fn [j]
        (map
         (fn [m] (list i j m))
         (range j (inc n))))
      (range i (inc n))))
   (range 1 n)))

(println (unique-triples 5))