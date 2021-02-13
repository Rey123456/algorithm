|排序方法|平均时间复杂度|最好|最坏|空间复杂度|稳定性|
|:-----|----|----|-----|----|-----|
|冒泡排序|O(n^2)|O(n)|O(n^2)|O(1)|稳定|
|选择排序|O(n^2)|O(n^2)|O(n^2)|O(1)|不稳定|
|插入排序|O(n^2)|O(n)|O(n^2)|O(1)|稳定|
|希尔排序|O(nlogn)-O(n^2)|O(n^1.3)|O(n^2)|O(1)|不|
|堆排序|O(nlogn)|O(nlogn)|O(nlogn)|O(1)|不稳定|
|归并排序|O(nlogn)|O(nlogn)|O(nlogn)|O(n)|稳定|
|快排|O(nlogn)|O(nlogn)|O(n^2)|O(1)|不稳定|
|桶排序|O(N+C);C=N\*(logN-logM);(M表示桶数)|O(N)|O(N+C)|O(N+M)|稳定|

桶排序(并行)
```
O(N) + O(M* (N/M)*log(N/M))=O(N+N*log(N/M))=O(N+N*(logN-logM))
当M=N时，=O(N)
```