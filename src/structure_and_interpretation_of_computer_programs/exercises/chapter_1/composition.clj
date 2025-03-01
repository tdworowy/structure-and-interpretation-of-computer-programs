(ns structure-and-interpretation-of-computer-programs.exercises.chapter-1.composition)

(defn compose [f g]
  (fn [x] (f (g x))))

(defn square [x]
  (* x x))

(println ((compose square inc) 6))