import kmeans._
import kmeans.KMeans

import scala.collection.GenSeq

val k = new KMeans()

val points = GenSeq((0, 0, 1), (0,0, -1), (0,1,0), (0,10,0))
val oldMeans = GenSeq((0, -1, 0), (0, 2, 0))
val eta = 12.25
k.kMeans(points map {case(x, y, z) => new Point(x, y, z)}, oldMeans map {case(x, y, z) => new Point(x, y, z)}, eta)