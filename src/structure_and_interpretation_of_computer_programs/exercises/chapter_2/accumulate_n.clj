(defn accumulate [op init sequence]
  (if (empty? sequence) init
      (recur op (op init (first sequence)) (rest sequence))))

(defn accumulate-n  [op init seqs]
  (if (empty? (first seqs)) '()
      (lazy-seq
       (cons (accumulate op init (map first seqs)) (accumulate-n op init (map rest seqs))))))

(println (accumulate-n + 0 [[1 2 3] [4 5 6] [7 8 9] [10 11 12]]))
