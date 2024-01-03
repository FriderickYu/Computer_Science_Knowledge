import torch

# 原始的自动微分过于底层, 在实际构建神经网络中, 经常考虑将计算划分为层
# 其中一些层具有可学习的参数, 这些参数将在学习过程中得到优化

# nn模块定义了一组Module, 大致相当于神经网络中的层, 一个Module接收输入张量并计算输出张量
# nn模块还定义了一组常用的损失函数, 这些损失函数在训练神经网络时经常被使用

device = torch.device('cpu')

N, D_in, H, D_out = 64, 1000, 100, 10
x = torch.randn(N, D_in)
y = torch.randn(N, D_out)

# 使用nn模块实现两层网络
# nn.Sequential是一个Module, 其包含了其他Module, 并按顺序应用他们以产生输出
model = torch.nn.Sequential(
    # Linear Module使用线性函数从输入计算到输出, 并保存其权重和偏置的内部张量
    torch.nn.Linear(D_in, H),
    torch.nn.ReLU(),
    torch.nn.Linear(H, D_out)
).to(device)

# 设置reduction='sum'表示计算的是平均误差的总和, 而非均值
loss_fn = torch.nn.MSELoss(reduction='sum')

learning_rate = 1e-4
for t in range(500):
    # 将输入的张量传给model, 输出的也是张量
    y_pred = model(x)
    loss = loss_fn(y_pred, y)
    print("第%d次迭代的损失率是:%f" %(t, loss.item()))

    # 在进行反向传播前就把梯度归零
    model.zero_grad()

    # 在模型内部, 每个model的参数都存储在requires_grad=True的张量中
    # 因此这个调用将计算模型中所有可学习的参数的梯度
    loss.backward()

    with torch.no_grad():
        for param in model.parameters():
            param.data -= learning_rate * param.grad