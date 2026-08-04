package com.shreyansh.LeetScan.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Set;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyansh.LeetScan.Agent.LeetScanAgent;

import dev.langchain4j.data.segment.TextSegment;

@Service
public class CrawlerService {
	
	@Autowired
	LeetScanAgent agent;
	
	@Autowired
	IngestionService ingestionService;

	private final GitHub github;
	
	List<TextSegment> allChunks = new ArrayList<>();
	private static final Set<String> SUPPORTED_EXTENSIONS = Set.of(
			".java", ".kt", ".groovy",
			".py",
			".js", ".jsx",
			".ts", ".tsx",
			".go",
			".rs",
			".cpp", ".cc", ".cxx", ".c", ".hpp", ".h",
			".cs",
			".php",
			".rb",
			".swift",
			".scala",
			".dart",
			".sql",
			".sh",
			".yaml", ".yml",
			".json",
			".xml",
			".toml",
			".gradle",
			".properties",
			".env.example",
			".md",
			".txt",
			".html",
			".css",
			".scss",
			".vue",
			".svelte"
	);

	private boolean isSupported(String path) {
		return SUPPORTED_EXTENSIONS.stream()
				.anyMatch(path::endsWith);
	}


	public CrawlerService(GitHub github) {
	    this.github = github;
	}
	
	public void fetchRepositoryFileByName(String repoName) throws IOException
	{
		GHRepository repo=github.getRepository(repoName);
		GHTree tree=repo.getTreeRecursive(repo.getDefaultBranch(),0);
		List<GHTreeEntry> listOfTree=tree.getTree();
//		System.out.println("connected to :"+ tree);
		for(GHTreeEntry t:listOfTree)
		{
			// Instead of parts[parts.length - 1]
			String fileName = java.nio.file.Paths.get(t.getPath()).getFileName().toString();
			    System.out.println(fileName);
			}
		}


	public void fetchRepositoryData(String repoName) throws IOException
	{
		GHRepository repo=github.getRepository(repoName);
		
		
		
		ingestionService.clearDatabase();
//		System.out.println("successfully connected to github!");
//		System.out.println("Repository :"+repo.getFullName());
		
		GHTree tree=repo.getTreeRecursive(repo.getDefaultBranch(),0);

		List<GHTreeEntry> supportedFiles = tree.getTree().stream()
				.filter(e -> e.getType().equals("blob"))
				.filter(e -> isSupported(e.getPath()))
				.toList();

		for (GHTreeEntry entry : supportedFiles) {
			GHContent content = repo.getFileContent(entry.getPath(), repo.getDefaultBranch());

			    // Use try-with-resources to read the stream safely
			    try (InputStream is = content.read();
			         Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
			        
			        StringBuilder fileBuilder = new StringBuilder();
			        while (scanner.hasNextLine()) {
			            fileBuilder.append(scanner.nextLine()).append("\n");
			        }

			        String rawCode = fileBuilder.toString();
			        
			        if (rawCode.trim().isEmpty()) {
		                System.out.println("Skipping empty file: " + entry.getPath());
		                continue; 
		            }
			        
			        ingestionService.Ingest(rawCode, entry.getPath());
//			        
//			        allChunks.addAll(chunks);
			        
			        
		
			        
			       
			        
			    } catch (Exception e) {
					System.err.println("Failed to read file: " + entry.getPath());
					e.printStackTrace();
				}
			}
//		       System.out.println("total size of number of chunks :"+allChunks.size());
//		       System.out.println("get the first chunk :"+chunks.getFirst());
			
	}

	public String askQuestion(String userPrompt) {
		return agent.chat(userPrompt);
		
	}	
	
}
