import time
import cv2
import numpy as np
import matplotlib.pyplot as plt

left = cv2.imread('cones0.png', cv2.IMREAD_GRAYSCALE)
right = cv2.imread('cones1.png', cv2.IMREAD_GRAYSCALE)

h, w = left.shape
block_size = 7
dmax = 64


bm = cv2.StereoBM_create(dmax, block_size)
start = time.time()
bm = cv2.StereoBM_create(dmax, block_size)
start = time.time()
disp = bm.compute(left,right)
print("Time required: %.2f sec"%(time.time()-start))
plt.imshow(disp, cmap='jet')
np.argmax