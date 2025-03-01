(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.subsets)

(defn subsets [s]
  (if (empty? s)
    '(())
    (let [rest-subsets (subsets (rest s))]
      (concat rest-subsets (map #(cons (first s) %) rest-subsets)))))

(println (subsets [1 2 3]))

