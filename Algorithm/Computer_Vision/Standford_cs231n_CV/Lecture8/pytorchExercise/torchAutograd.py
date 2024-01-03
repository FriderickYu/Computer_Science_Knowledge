import torch

# 接下来将会使用Pytorch的自动求导特性来代替手动求导的步骤
# Automatic differentiation 自动微分
# https://zh.wikipedia.org/wiki/%E8%87%AA%E5%8B%95%E5%BE%AE%E5%88%86
# 在forward过程中, 将定义一个computational graph, node就是张量, edge就是从输入张量产生输出张量的函数

# We wanna this to run on GPU
# device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
device = torch.device('cpu')

# N代表batch size, D_in代表输入的维度
# H代表隐含层的维度, D_out代表输出的维度

N, D_in, H, D_out = 64, 1000, 100, 10

# 输入x: 64行, 1000列
x = torch.randn(N, D_in)
# 输出y: 64行, 10列
y = torch.randn(N, D_out)

# 初始化权重
# 标准正态分布, 随机生成[0, 1)之间的随机数(张量), 张量的形状由第一个参数sizes定义
# 如果想要计算相对于某个张量的梯度, 那么构造该张量时需要设置requires_grad=True
# 对该张量进行任何Pytorch操作都将导致构建一个计算图, 使能够稍后通过图执行反向传播
# 例如x是一个requires_grad=True的张量, 那么在反向传播后x.grad将是另一种张量, 保存着x相对于某个标量值的梯度
w1 = torch.randn(D_in, H, requires_grad=True)
w2 = torch.randn(H, D_out, requires_grad=True)

learning_rate = 1e-6
for t in range(500):
    # 因为w1和w2设置了requires_grad=True, 所以涉及到w1和w2的操作都会构建一个计算图, 从而实现梯度的自动计算
    # 由于不再需要手动实现反向传播, 因此不需要保留中间值的引用
    h = x.mm(w1)
    h_relu = h.clamp(min=0)
    y_pred = h_relu.mm(w2)
    loss = (y_pred - y).pow(2).sum()
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))

    # backprop, 这个调用将计算损失函数相对于所有requires_grad=True张量的梯度
    # 调用之后, w1.grad和w2.grad将是保存着损失函数相对于w1和w2梯度的张量
    loss.backward()

    # 使用梯度下降更新权重, 因为这一步只是想更新w1和w2的值; 但在更新的过程并不希望重新更新计算图
    # 因此需要使用torch.no_grad()上下文管理器来阻止Pytorch构建计算图
    with torch.no_grad():
        w1 -= learning_rate * w1.grad
        w2 -= learning_rate * w2.grad

        # 在一次反向传播后, 会随之更新参数, 需要手动将w1和w2的梯度置零
        # 以免梯度的积累影响到下一次的梯度计算
        w1.grad.zero_()
        w2.grad.zero_()
