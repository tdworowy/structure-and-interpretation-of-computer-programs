(defn reverse-vector [items]
  (letfn [(reverse-vector-iter [vec new-vec n]
            (if (neg? n)
              new-vec
              (reverse-vector-iter vec (conj new-vec (nth vec n)) (dec n))))]
    (reverse-vector-iter items [] (dec (count items)))))

(defn deep-reverse-vector [items]
  (letfn [(iter-reverse [items]
            (if (empty? items)
              []
              (let [first-item (first items)
                    rest-reversed (iter-reverse (rest items))]
                (if (vector? first-item)
                  (conj rest-reversed (deep-reverse-vector first-item))
                  (conj rest-reversed first-item)))))]
    (iter-reverse items)))

(defn reverse-vector2 [items]
  (letfn [(reverse-vector-iter [vec new-vec n]
            (if (neg? n)
              new-vec
              (recur vec (conj new-vec (nth vec n)) (dec n))))]
    (reverse-vector-iter items [] (dec (count items)))))

(println (reverse-vector [1 2 3 4]))
(println (reverse-vector2 [1 2 3 4]))
(println (deep-reverse-vector [1 2 3 4]))
(println (deep-reverse-vector [1 [2 3] 4]))