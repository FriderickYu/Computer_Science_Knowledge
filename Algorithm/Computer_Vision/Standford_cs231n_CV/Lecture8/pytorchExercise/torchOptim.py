import torch

# 使用nn模块来定义模型和, 这里使用optim包提供的Adam算法来优化模型

N, D_in, H, D_out = 64, 1000, 100, 10

# 输入x: 64行, 1000列
x = torch.randn(N, D_in)
# 输出y: 64行, 10列
y = torch.randn(N, D_out)

# 定义网络
model = torch.nn.Sequential(
    torch.nn.Linear(D_in, H),
    torch.nn.ReLU(),
    torch.nn.Linear(H, D_out)
)
loss_fn = torch.nn.MSELoss(reduction='sum')

learning_rate = 1e-4

# 使用optim包来定义一个优化器, 其将为更新模型的权重
# Adam构造函数的第一个参数告诉优化器应该更新哪些张量
optimizer = torch.optim.Adam(model.parameters(), lr=learning_rate)
for t in range(500):
    y_pred = model(x)
    loss = loss_fn(y_pred, y)
    print("第%d次迭代的损失率是:%f" % (t, loss.item()))
    # 在反向传播之前, 使用优化器对象将它将要更新的张量的所有梯度清零
    optimizer.zero_grad()
    loss.backward()
    # 更新optimizer中的函数进行更新
    optimizer.step()
