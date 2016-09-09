import kmeans._
import kmeans.KMeans

import scala.collection.GenSeq

val k = new KMeans()

val points = GenSeq((0, 0, 1), (0,0, -1), (0,1,0), (0,10,0)) map {case(x, y, z) => new Point(x, y, z)}
val oldMeans = GenSeq((0, -1, 0), (0, 2, 0)) map {case(x, y, z) => new Point(x, y, z)}
val eta = 12.25


val groups = points groupBy (p => k.findClosest(p, oldMeans))
val emptyClass = oldMeans.toSet.diff(groups.keySet)
val classified = groups ++ (emptyClass zip List.fill(emptyClass.size)(GenSeq()))
classified

val newMeans = oldMeans map (old => k.findAverage(old, classified(old)))
newMeans

oldMeans zip newMeans map {case (o, n) => o.squareDistance(n) <= eta} reduce (_ & _)


k.kMeans(points, oldMeans, eta)