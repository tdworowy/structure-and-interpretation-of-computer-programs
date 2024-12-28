(defn square [x]
  (* x x))

(defn square-list1 [items]
  (defn iter [things answer]
    (if (empty? things) answer
        (iter (rest things) (cons (square (first things)) answer))))
  (iter items nil))

(defn square-list2 [items]
  (defn iter [things answer]
    (if (empty? things) answer
        (iter (rest things) (cons answer (square (first things))))))
  (iter items nil)); error second argument of cons need to be list

(defn square-list3 [items]
  (letfn [(iter [things answer]
            (if (empty? things) answer
                (iter (rest things) (cons (square (first things)) answer))))]
    (iter items nil)))

(println (square-list1 [2 4 5]))
(println (square-list3 [2 4 5]))
