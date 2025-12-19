package com.example.service;

import com.example.entity.Block;
import java.util.List;

public interface IBlockService {
    List<Block> getAllBlocks();
    Block createBlock(String blockName);
}
