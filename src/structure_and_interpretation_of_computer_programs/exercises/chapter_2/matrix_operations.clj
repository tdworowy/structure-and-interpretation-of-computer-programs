(defn accumulate [op init sequence]
  (if (empty? sequence) init
      (recur op (op init (first sequence)) (rest sequence))))

(defn accumulate-n  [op init seqs]
  (if (empty? (first seqs)) '()
      (lazy-seq
       (cons (accumulate op init (map first seqs)) (accumulate-n op init (map rest seqs))))))

(defn dot-product [v w]
  (reduce + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map #(dot-product v %) m))

(defn transpose [mat]
  (apply map list mat))

(defn matrix-*-matrix [m n]
  (map (fn [x] (map (fn [y] (dot-product x y)) (transpose n))) m))

(println (accumulate-n + 0 [[1 2 3] [4 5 6] [7 8 9] [10 11 12]]))
(println (dot-product [1 2 3] [4 5 6]))
(println (matrix-*-vector  [[1 2 3] [1 2 3]] [4 5 6]))
(println (transpose  [[1 2 3] [4 5 6]]))
(println (matrix-*-matrix  [[1 2 3] [1 2 3]] [[4 5 6] [4 5 6]]))
