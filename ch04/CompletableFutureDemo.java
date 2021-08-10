public class CompletableFutureDemo {
    public static void main(String[] args) {
        //原始数据集
        CopyOnWriteArrayList<Integer> taskList = new CopyOnWriteArrayList();
        taskList.add(1);
        taskList.add(2);
        taskList.add(3);
        taskList.add(4);

        // 结果集
        List<Character> resultList = new ArrayList<>();
        //线程池，可容纳四个线程
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture[] cfs = taskList.stream()
                //第一阶段
                .map(integer -> CompletableFuture.supplyAsync(
                        () -> calcASCII(integer), executorService)
                        //第二阶段
                        .thenApply(i -> {
                            char c = (char) (i.intValue());
                            System.out.println("【阶段2】线程"
                                    + Thread.currentThread().getName() + "执行完毕，" 
+ "已将int"
                                    + i + "转为了字符" + c);
                            return c;
                        })
                        //第三阶段
                        .whenComplete((ch, e) -> {
                            resultList.add(ch);
                            System.out.println("【阶段3】线程" +
                                    Thread.currentThread().getName() + "执行完毕，" + "已将"
                                    + ch + "增加到了结果集" + resultList + "中");
                            executorService.shutdown();
                        })
                ).toArray(CompletableFuture[]::new);

        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();//future.get()

        System.out.println("完成！result=" + resultList);
    }

    //计算i的ASCII值
    public static Integer calcASCII(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            //数字 -> A-D对应的ascii
            i = i + 64;
            System.out.println("【阶段1】线程" + Thread.currentThread().getName() 
+ "执行完毕，" + "已将" + i
 + "转为了A(或B或C或D)对应的ASCII" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}