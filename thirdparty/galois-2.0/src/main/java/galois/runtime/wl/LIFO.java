/*
Galois, a framework to exploit amorphous data-parallelism in irregular
programs.

Copyright (C) 2010, The University of Texas at Austin. All rights reserved.
UNIVERSITY EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES CONCERNING THIS SOFTWARE
AND DOCUMENTATION, INCLUDING ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR ANY
PARTICULAR PURPOSE, NON-INFRINGEMENT AND WARRANTIES OF PERFORMANCE, AND ANY
WARRANTY THAT MIGHT OTHERWISE ARISE FROM COURSE OF DEALING OR USAGE OF TRADE.
NO WARRANTY IS EITHER EXPRESS OR IMPLIED WITH RESPECT TO THE USE OF THE
SOFTWARE OR DOCUMENTATION. Under no circumstances shall University be liable
for incidental, special, indirect, direct or consequential damages or loss of
profits, interruption of business, or related expenses which may arise from use
of Software or Documentation, including but not limited to those resulting from
defects in Software and/or Documentation, or loss or inaccuracy of data of any
kind.


*/





package galois.runtime.wl;

import galois.runtime.ForeachContext;

import java.util.ArrayDeque;

/**
 * Order elements in last-in-first-out order, i.e., stack order.
 * 
 *
 * @param <T>  the type of elements of the worklist
 * @see FIFO
 * @see ChunkedLIFO
 * @see BoundedLIFO
 */
@OnlyLeaf
@MatchingConcurrentVersion(ConcurrentLIFO.class)
@MatchingLeafVersion(LIFO.class)
public class LIFO<T> implements Worklist<T> {
  private ArrayDeque<T> queue;

  public LIFO(Maker<T> maker, boolean needSize) {
    queue = new ArrayDeque<T>();
  }

  @Override
  public Worklist<T> newInstance() {
    return new LIFO<T>(null, false);
  }

  @Override
  public void add(T item, ForeachContext<T> ctx) {
    queue.add(item);
  }

  @Override
  public void addInitial(T item, ForeachContext<T> ctx) {
    add(item, ctx);
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  @Override
  public T poll(ForeachContext<T> ctx) {
    return queue.pollLast();
  }

  @Override
  public int size() {
    return queue.size();
  }

  @Override
  public void finishAddInitial() {

  }
}
