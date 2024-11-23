(defn compose [f g]
  (fn [x] (f (g x))))

(defn square [x]
  (* x x))

(println ((compose square inc) 6))