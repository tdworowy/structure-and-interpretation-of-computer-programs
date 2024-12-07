(defn reverse-vector [_vector]
  (letfn [(reverse-list-iter [vec new-vec n]
            (if (neg? n)
              new-vec
              (reverse-list-iter vec (conj new-vec (nth vec n)) (dec n))))]
    (reverse-list-iter _vector [] (dec (count _vector)))))

(println (reverse-vector [1 2 3 4]))