package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coffee.covidtrace.Adapter.faq_name_adapter;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<faqs> faqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        recyclerView = findViewById(R.id.recycle);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView(){
        faq_name_adapter faq_name_adapter = new faq_name_adapter(faqList);
        recyclerView.setAdapter(faq_name_adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        faqList = new ArrayList<>();

        faqList.add(new faqs("IS COVID-19 WORSE THAN FLU?", "COVID-19 has a higher rate of severe disease and mortality in nearly every age group, compared with influenza (flu).\n\n" +
                "John Hopkins Bloomberg School of Public Health reported that “COVID-19 has killed more people in the US than flu has in the last five years.”\n\n" +
                "In the UK, data revealed that between January-August, COVID-19 killed 3.4 times as many people in 2020 than flu and pneumonia combined.\n\n" +
                "The US Centers for Disease Control and Prevention (CDC) estimate that in the United States flu has resulted in “between 12,000-61,000 deaths annually since 2010.” COVID-19 has resulted in more than 304,000 deaths in the US. "));
        faqList.add(new faqs("HOW DOES COVID-19 COMPARE TO OTHER PUBLIC HEALTH THREATS?", "This depends very much on where people live and how old they are, with some parts of the world hit harder than others, and some age groups being affected more, such as older people. However, even in countries where the COVID-19 death rate seems relatively low, the indirect impact on reduced health services, vaccine supply shortages, and reluctance to seek medical treatment for fear of infection is having a drastic impact and putting tens of millions of people at risk – including children and newborns.\n\n" +
                "Even though COVID-19 appears to directly affect children less than adults, it can have indirect impacts. Modelling studies have predicted that COVID-19 could amplify neonatal deaths. For instance, research published by the Guttmacher Institute in April estimated that even a 10% decrease in pregnancy-related and neonatal health care could result in an extra 168,000 neonatal and 28,000 maternal deaths globally, as well as 2.5 million newborns experiencing major medical complications.\n\n" +
                "When COVID cases peaked in Europe, it killed almost twice as many Europeans as cancer which is the biggest killer under normal circumstances. "));
        faqList.add(new faqs("HOW EFFECTIVE ARE MASKS AND DO THEY ALSO NEED TO COVER MY NOSE?", "Mask wearing is a very simple and effective way to reduce transmission and save lives. The degree of protection depends on the type of mask, how well they worn and on other factors such as the behaviour of people wearing them and the behaviour of air droplets in different settings.\n\n" +
                "Masks alone are not enough to fully protect someone from the virus, but they still have an essential role to play in protecting high risk individuals, such as frontline health care workers. For most people the greatest benefit lies not in protecting the wearer, but those around them. When combined with other safety measures like physical distancing and practicing good hygiene, they are a very effective tool with little to no disruption to normal life.\n\n" +
                "COVID-19 is predominantly spread via respiratory droplets that are released when someone that has been infected coughs, sneezes or talks. Breathing those droplets in through your nose can lead to infection. Masks act as a barrier preventing people from spreading these potentially infectious droplets.\n\n" +
                "Using a mask to just cover the mouth and not the nose only addresses part of risk, and so could defeat the whole purpose of wearing one in the first place. Similarly, eye protection can also be important, particularly when physical distancing isn’t possible.  "));
        faqList.add(new faqs("WHY DO GOVERNMENTS BENEFIT FROM HELPING TO ENSURE OTHER COUNTRIES ACCESS VACCINES?", "Ensuring COVID-19 vaccines reach people in the world’s poorest countries isn’t just about being charitable or acting on a moral imperative; it’s also in the best interest of every country to do so. The pandemic started in Wuhan, China and spread to 180 countries within three months. That means if the virus is circulating in one country, the rest of the world remains at risk.\n\n" +
                "Until every country has access to vaccines and they have been administered to a high enough proportion of the population to considerably reduce death and severe disease from COVID-19, commerce, international trade and tourism cannot resume which will continue to negatively impact economies.\n\n" +
                "Countries hoarding vaccines is known as vaccine nationalism, which is what happened with the 2009 H1N1 swine flu pandemic. If this happens with COVID-19 vaccines it could prolong the pandemic, leading to more deaths and slower recovery. Research analysing the benefit-cost ratio of investing in the procurement and delivery of safe and effective COVID-19 vaccines to the world’s poorest countries found that for every US$ 1 spent, wealthier countries would get back about US$ 4.80 in terms of the avoided economic costs.\n\n" +
                "The quickest way to end the acute phase of this pandemic and limit the economic damage is for all countries to have equitable access to and effective delivery of COVID-19 vaccines. "));
        faqList.add(new faqs("WOULD IT BE POSSIBLE TO ACHIEVE HERD IMMUNITY WITHOUT VACCINES?", "Historically, the most successful way to achieve herd immunity is actually through mass vaccination. This is a much safer route as the alternative is for roughly 60% of the population to be infected with COVID-19 which essentially means leaving the virus unchecked.\n\n" +
                "Attempting to achieve natural herd immunity through infection will lead to a rise in cases, and a surge in the number of deaths. Moreover, natural immunity to coronaviruses usually only lasts for up to a couple of years. As Kristian Andersen, an immunologist at the Scripps Research Institute in California warned, this approach could “lead to unacceptable and unnecessary untold human death and suffering.” "));
        faqList.add(new faqs("HOW CAN WE TRUST VACCINES THAT HAVE BEEN DEVELOPED SO FAST?", "COVID-19 vaccines have been developed and produced in record time, but it’s also involved unprecedented levels of funding and global cooperation to tackle a global threat. So far, all approved COVID-19 vaccines have undergone large clinical trials with rigorous safety protocols, and without major safety concerns raised.\n\n" +
                "The COVAX Facility – co-led by Gavi, the Coalition for Epidemic Preparedness Innovations (CEPI) and the World Health Organization (WHO) – has incentivised vaccine manufacturers to scale-up and manufacture high volumes before the vaccines are licensed. This is known as manufacturing at risk but it means that vaccines can be rolled out as soon as they are approved, avoiding years of delay. "));
        faqList.add(new faqs("WHAT ARE THE RISKS OF RE-INFECTION?", "As with other coronaviruses, such as those that cause the common cold, SARS-CoV-2 can reinfect people. There have been multiple reports of reinfection, including a 25-year-old man in the US who tested positive twice. The Lancet reported that “the second infection was symptomatically more severe than the first.”\n\n" +
                "Even if you have had a test that detects antibodies, reinfection is still possible, although less likely.\n\n" +
                "Antibodies are only one part of the vast, complex immune response that is set in motion when a person becomes infected with a virus like SARS-CoV-2 so having antibodies isn’t enough to guarantee long-term protection.\n\n" +
                "In many people, infection with SARS-CoV-2 can lead to so-called ‘long COVID’ – where people suffer long-term complications resulting from the virus. This can be caused directly by the virus replicating over long periods, or indirectly with symptoms, like fatigue, waxing and waning even after the virus is present in their body."));
        faqList.add(new faqs("IS REGULARLY WASHING MY HANDS ENOUGH TO PROTECT ME?", "Even though regularly washing your hands is very important, as with masks it is not enough on its own. Public health guidelines all work in tandem to prevent the spread of germs.\n\n" +
                "Keep washing your hands for at least 20 seconds each time, keep carrying antibacterial gel when you’re travelling and remember to wear a mask and practice physical distancing."));
        faqList.add(new faqs("DO I STILL NEED TO WORRY ABOUT INFECTION EVEN THOUGH I’M FIT AND HEALTHY?", "Search for keywords like ‘fit,’ ‘healthy’ and ‘COVID’ and you will see countless headlines referring to people who have died or suffered from severe symptoms despite being otherwise healthy. So even if you are less at risk due to your age or BMI, that doesn’t guarantee that you won’t suffer from long-term symptoms like breathlessness, fatigue, a loss of smell and/or taste.\n\n" +
                "A research paper on COVID-19-related mortality among US adults aged 25-44 revealed that in July 2020 almost 12,000 more deaths were recorded than normally expected, based on historical norms. This led to July being described as the deadliest month for this age group in 20 years. Although not every death was from COVID-19, it was the driving force for this tragic figure.\n\n" +
                "Even if you are fit and healthy, you could spread the infection to vulnerable people, such as older people or those with compromised immune systems or diabetes. Taking steps to safeguard against COVID-19 will not only protect you, but also others around you. "));
        faqList.add(new faqs("SHOULD I BE CONCERNED THAT THE SAMPLE SIZES IN VACCINE CLINICAL TRIALS WEREN’T BIGGER?", "A small sample size in clinical trials doesn’t necessarily reduce the importance of the findings. The more efficacious the intervention, the smaller the number of clinical outcomes needed to demonstrate its efficacy because the benefit of the intervention is so clear. You would expect larger sample sizes when there are more grey areas about how effective an intervention is.\n\n" +
                "COVID-19 is spreading so rapidly that needlessly delaying vaccine production in favour or having a larger clinical trial would put more people at risk. With such a fast-moving virus and so many people at risk, time is of the essence."));
    }
}