package com.example.service.impl;

import com.example.entity.Block;
import com.example.repository.BlockRepository;
import com.example.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlockServiceImpl implements IBlockService {

    @Autowired
    BlockRepository blockRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    @Override
    @Transactional
    public Block createBlock(String blockName) {
        Block block = new Block();
        block.setName(blockName);
        return blockRepository.save(block);
    }
}
