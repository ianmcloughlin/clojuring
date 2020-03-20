; Plus plus
(defn ++ [x] (+ x 1))
(defn -- [x] (- x 1))

(defn plus [x, y]
 (if (= y 0)
  x
  (+ 1 (plus x (-- y)))))

(println (plus 10 11))

(defn mult [x, y]
 (if (= y 1)
  x
  (+ x (mult x (-- y)))))

(println (mult 10 11))