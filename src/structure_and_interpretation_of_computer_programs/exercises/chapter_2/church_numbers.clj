(ns structure-and-interpretation-of-computer-programs.exercises.chapter-2.church-numbers)

(defn zero [] (fn [f] (fn [x] x)))
(defn add-1 [n] (fn [f] (fn [x] (f ((n f) x)))))
(defn one [] (fn [f] (fn [x] (f (f (x))))))
(defn two [] (fn [f] (fn [x] (f (f (f (x)))))))
(defn add [m n] (fn [f] (fn [x] ((m f) ((n f) x)))))
