/* Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.mahout.utils;

import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.matrix.CardinalityException;
import org.apache.mahout.matrix.Vector;

/**
 * This class implements a "manhattan distance" metric by summing the absolute
 * values of the difference between each coordinate
 */
public class ManhattanDistanceMeasure implements DistanceMeasure {

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.mahout.clustering.canopy.DistanceMeasure#distance(java.lang.Float[],
   *      java.lang.Float[])
   */
  public float distance(Float[] p1, Float[] p2) {
    float result = 0;
    for (int i = 0; i < p1.length; i++)
      result += Math.abs(p2[i] - p1[i]);
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.hadoop.mapred.JobConfigurable#configure(org.apache.hadoop.mapred.JobConf)
   */
  public void configure(JobConf job) {
    // nothing to do
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.mahout.utils.DistanceMeasure#distance(org.apache.mahout.matrix.Vector,
   *      org.apache.mahout.matrix.Vector)
   */
 public double distance(Vector v1, Vector v2) throws CardinalityException {
    if (v1.cardinality() != v2.cardinality())
      throw new CardinalityException();
    double result = 0;
    for (int i = 0; i < v1.cardinality(); i++)
      result += Math.abs(v2.getQuick(i) - v1.getQuick(i));
    return result;
  }

}
