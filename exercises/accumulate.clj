(defn accumulate [combiner null-value term a next b]
  (if (> a b) null-value
      (combiner (term a) (accumulate combiner null-value term (next a) next b))))

(defn accumulate-tail [combiner null-value term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (combiner result (term a)))))
  (iter a null-value))

(defn sum [term a next b]
  (accumulate-tail + 0 term a next b))

(defn product [term a next b]
  (accumulate-tail * 1 term a next b))

